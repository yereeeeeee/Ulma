package com.ssafy11.api.service;

import com.ssafy11.api.config.util.VerificationUtil;
import com.ssafy11.api.dto.SmsVerification;
import com.ssafy11.api.exception.ErrorCode;
import com.ssafy11.api.exception.ErrorException;
import com.ssafy11.domain.common.PageDto;
import com.ssafy11.domain.common.PageResponse;
import com.ssafy11.domain.participant.ParticipantDao;
import com.ssafy11.domain.participant.dto.AddGuestResponse;
import com.ssafy11.domain.participant.dto.Participant;
import com.ssafy11.domain.participant.dto.Transaction;
import com.ssafy11.domain.participant.dto.UserRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {
    private final ParticipantDao participantDao;

    @Transactional(readOnly = true)
    public List<UserRelation> sameName(Integer userId, String name){
        Assert.notNull(userId, "userId is required");
        Assert.notNull(name, " name is required");
        List<UserRelation> userRelationList = participantDao.sameName(userId, name);
        Assert.notNull(userRelationList, "userRelationList is required");
        return userRelationList;
    }

    @Transactional(readOnly = true)
    public PageResponse<Transaction> getTransactions(Integer userId, Integer guestId, PageDto pagedto){
        Assert.notNull(userId, "userId is required");
        Assert.notNull(guestId, "guestId is required");
        PageResponse<Transaction> transactionsList = participantDao.getTransactions(userId, guestId, pagedto);
        Assert.notNull(transactionsList, "transactionsList is required");
        return transactionsList;
    }

    public boolean isParticipant(final Integer eventId, final Integer participantId) {
        Assert.notNull(eventId, "eventId is required");
        Assert.notNull(participantId, "participantId is required");
        return participantDao.isParticipant(eventId, participantId);
    }

    public Integer addParticipant(Participant participant){
        Assert.notNull(participant, "participant is required");

        if(isParticipant(participant.eventId(), participant.guestId())){
            throw new ErrorException(ErrorCode.Duplicated);
        }

        Integer participantId = participantDao.addParticipant(participant);
        Assert.notNull(participantId, "participantId is required");
        return participantId;
    }

    public Integer addGuestAndUserRelation(AddGuestResponse addGuestResponse){
        Assert.notNull(addGuestResponse, "addGuestResponse is required");
        Integer guestId = participantDao.addGuests(addGuestResponse.name(), addGuestResponse.category());
        Assert.notNull(guestId, "guestId is required");
        Integer returnValue = participantDao.addUserRelation(guestId, addGuestResponse.userId());
        Assert.notNull(returnValue, "returnValue is required");
        return returnValue;
    }

    public PageResponse<UserRelation> getUserRelation(Integer userId, PageDto pagedto) {
        Assert.notNull(userId, "userId is required");
        PageResponse<UserRelation> userRelationList = participantDao.getUserRelations(userId, pagedto);
        Assert.notNull(userRelationList, "userRelationList is required");
        return userRelationList;
    }

}