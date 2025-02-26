package com.ssafy11.api.service;

import com.ssafy11.api.exception.ErrorCode;
import com.ssafy11.api.exception.ErrorException;
import com.ssafy11.domain.common.PageDto;
import com.ssafy11.domain.common.PageResponse;
import com.ssafy11.domain.events.EventDao;
import com.ssafy11.domain.participant.ParticipantDao;
import com.ssafy11.domain.participant.dto.*;
import com.ssafy11.domain.schedule.ScheduleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {
    private final ParticipantDao participantDao;
    private final EventDao eventDao;
    private final ScheduleDao scheduleDao;


    @Transactional(readOnly = true)
    public PageResponse<UserRelation> sameName(String userId, String name, String category, PageDto pagedto){
        Assert.hasText(userId, "UserId must not be null");
        PageResponse<UserRelation> userRelationList = participantDao.sameName(Integer.parseInt(userId), name, category, pagedto);
        Assert.notNull(userRelationList, "userRelationList is required");
        List<UserRelation> list = userRelationList(userRelationList.data(), userId);
        Assert.notNull(list, "list is required");

        int page = userRelationList.currentPage();
        int totalItems = userRelationList.totalItemsCount();
        int totalPages = userRelationList.totalPages();
        Assert.notNull(userRelationList, "userRelationList is required");
        return new PageResponse<>(list, page, totalItems, totalPages);

    }

    @Transactional(readOnly = true)
    public PageResponse<Transaction> getTransactions(String userId, Integer guestId, PageDto pagedto){
        Assert.hasText(userId, "UserId must not be null");
        Assert.notNull(guestId, "guestId is required");
        PageResponse<Transaction> transactionsList = participantDao.getTransactions(Integer.parseInt(userId), guestId, pagedto);
        Assert.notNull(transactionsList, "transactionsList is required");
        return transactionsList;
    }

    @Transactional(readOnly = true)
    public TransactionSummary getTransactionSummary(String userId, Integer guestId){
        Assert.hasText(userId, "UserId must not be null");
        Assert.notNull(guestId, "guestId is required");
        Assert.isTrue(scheduleDao.isMyGuest(Integer.parseInt(userId), guestId), "지인 관계가 아닙니다.");
        TransactionSummary summary = participantDao.getTransactionSummary(Integer.parseInt(userId), guestId);
        Assert.notNull(summary, "transactionSummary is required");
        return summary;
    }

    public boolean isParticipant(final Integer eventId, final Integer participantId) {
        Assert.notNull(eventId, "eventId is required");
        Assert.notNull(participantId, "participantId is required");
        return participantDao.isParticipant(eventId, participantId);
    }

    public Integer addParticipants(List<Participant> participants, String userId) {
        Assert.notNull(participants, "participants is required");

        for (Participant participant : participants) {
            Assert.isTrue(eventDao.isUserEventCreated(participant.eventId(), Integer.parseInt(userId)), "사용자가 만든 이벤트가 아닙니다.");

            if (isParticipant(participant.eventId(), participant.guestId())) {
                throw new ErrorException(ErrorCode.Duplicated);
            }

            Assert.isTrue(scheduleDao.isMyGuest(Integer.parseInt(userId), participant.guestId()), "지인 관계가 아닙니다.");
            Assert.isTrue(participant.amount() > 0, "값이 양수여야 합니다.");
        }

        Integer savedParticipantsCount = participantDao.addParticipants(participants);
        Assert.notNull(savedParticipantsCount, "savedParticipantsCount is required");
        Assert.isTrue(savedParticipantsCount == participants.size(), "일부 참가자 등록에 실패하였습니다.");

        return savedParticipantsCount;
    }

    public Integer updateParticipant(Participant participant, String userId) {
        Assert.notNull(participant, "participant is required");
        Assert.isTrue(eventDao.isUserEventCreated(participant.eventId(), Integer.parseInt(userId)), "사용자가 만든 이벤트가 아닙니다.");

        Assert.isTrue(scheduleDao.isMyGuest(Integer.valueOf(userId), participant.guestId()),"지인관계가 아닙니다.");

        if(!Objects.equals(participant.guestId(), participant.preGuestId())){
            Assert.isTrue(!isParticipant(participant.eventId(), participant.guestId()), "이미 등록된 참가자입니다. 해당 참가자를 수정해주세요.");
        }

        Integer resultId = participantDao.updateParticipant(participant);
        Assert.notNull(resultId, "resultId must not be null");
        return resultId;
    }

    public Integer deleteParticipant(Participant participant, String userId) {
        Assert.notNull(participant, "participant is required");
        Assert.isTrue(eventDao.isUserEventCreated(participant.eventId(), Integer.parseInt(userId)), "사용자가 만든 이벤트가 아닙니다.");

        Integer resultId = participantDao.deleteParticipant(participant);
        Assert.notNull(resultId, "resultId must not be null");
        return resultId;
    }

    public boolean isPhoneNumber(String phoneNumber, Integer userId){
        return participantDao.isPhoneNumber(phoneNumber,userId);
    }

    public Integer addGuestAndUserRelation(List<AddGuestResponse> addGuestResponse, String userId){
        Assert.notNull(addGuestResponse, "addGuestResponse is required");

        List<Integer> guestIds = new ArrayList<>();

        for (AddGuestResponse response : addGuestResponse) {
            Assert.isTrue(!isPhoneNumber(response.phoneNumber(), Integer.parseInt(userId)), "중복되는 휴대폰 번호가 존재합니다.");

            Integer guestId = participantDao.addGuests(response.name(), response.category(), response.phoneNumber());
            Assert.notNull(guestId, "guestId is required");
            guestIds.add(guestId);
        }

        Integer returnValue = participantDao.addUserRelations(guestIds, Integer.parseInt(userId));
        Assert.notNull(returnValue, "returnValue is required");

        Assert.isTrue(guestIds.size()==returnValue, "지인 등록에 실패하였습니다.");
        return returnValue;
    }

    @Transactional(readOnly = true)
    public PageResponse<UserRelation> getUserRelation(String userId, PageDto pagedto) {
        Assert.hasText(userId, "userId is required");
        PageResponse<UserRelation> userRelationList = participantDao.getUserRelations(Integer.parseInt(userId), pagedto);
        List<UserRelation> list = userRelationList(userRelationList.data(), userId);
        Assert.notNull(list, "list is required");

        int page = userRelationList.currentPage();
        int totalItems = userRelationList.totalItemsCount();
        int totalPages = userRelationList.totalPages();
        Assert.notNull(userRelationList, "userRelationList is required");
        return new PageResponse<>(list, page, totalItems, totalPages);
    }

    public List<UserRelation> userRelationList(List<UserRelation> result, String userId){
        List<UserRelation> UserRelations = new ArrayList<>();
        for (UserRelation relation : result) {
            TransactionSummary summary = getTransactionSummary(userId, relation.guestId());
            UserRelations.add(new UserRelation(
                    relation.guestId(),
                    relation.name(),
                    relation.category(),
                    relation.phoneNumber(),
                    summary.totalBalance()
            ));
        }
        return UserRelations;
    }

}
