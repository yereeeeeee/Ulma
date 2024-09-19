package com.ssafy11.domain.participant;

import com.ssafy11.domain.common.PageDto;
import com.ssafy11.domain.common.PaginatedResponse;
import com.ssafy11.domain.participant.dto.Participant;
import com.ssafy11.domain.participant.dto.Transaction;
import com.ssafy11.domain.participant.dto.UserRelation;
import org.jooq.Record1;

import java.util.List;

public interface ParticipantDao {
    List<UserRelation> sameName(Integer userId, String name);
    PaginatedResponse<Transaction> getTransactions(Integer userId, Integer guestId, PageDto pageDto);
    Integer addParticipant(Participant participant);
    Integer addGuests(String name, String category);
    Integer addUserRelation(Integer guestId, Integer userId);
    PaginatedResponse<UserRelation> getUserRelations(Integer userId, PageDto pageDto);
}
