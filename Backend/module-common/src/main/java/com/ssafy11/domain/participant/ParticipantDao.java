package com.ssafy11.domain.participant;

import com.ssafy11.domain.common.PageDto;
import com.ssafy11.domain.common.PageResponse;
import com.ssafy11.domain.participant.dto.Participant;
import com.ssafy11.domain.participant.dto.Transaction;
import com.ssafy11.domain.participant.dto.UserRelation;

import java.util.List;

public interface ParticipantDao {
    List<UserRelation> sameName(Integer userId, String name);
    PageResponse<Transaction> getTransactions(Integer userId, Integer guestId, PageDto pageDto);
    Boolean isParticipant(Integer eventId, Integer participantId);
    Integer addParticipant(Participant participant);
    Integer addGuests(String name, String category);
    Integer addUserRelation(Integer guestId, Integer userId);
    PageResponse<UserRelation> getUserRelations(Integer userId, PageDto pageDto);

}