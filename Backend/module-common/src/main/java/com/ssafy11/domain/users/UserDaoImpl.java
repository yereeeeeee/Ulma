package com.ssafy11.domain.users;

import static com.ssafy11.ulma.generated.Tables.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ssafy11.domain.accounts.Account;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class UserDaoImpl implements UserDao {

	private final DSLContext dsl;

	@Transactional
	@Override
	public Integer save(UserCommand command) {
		Record1<Integer> one = dsl.insertInto(
				USERS,
				USERS.LOGIN_ID,
				USERS.EMAIL,
				USERS.PASSWORD,
				USERS.NAME,
				USERS.PHONE_NUMBER,
				USERS.CREATED_AT)
			.values(command.loginId(), command.email(), command.password(), command.name(), command.phoneNumber(), LocalDateTime.now())
			.returningResult(USERS.ID)
			.fetchOne();

		Assert.notNull(one.getValue(USERS.ID), "ID 에 null 값은 허용되지 않음");
		return one.getValue(USERS.ID);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return dsl.fetchExists(
			dsl.selectOne()
				.from(USERS)
				.where(USERS.PHONE_NUMBER.eq(phoneNumber))
		);
	}

	@Override
	public boolean existsByEmail(String email) {
		return dsl.fetchExists(
			dsl.selectOne()
				.from(USERS)
				.where(USERS.EMAIL.eq(email))
		);
	}

	@Override
	public boolean existsByLoginId(String loginId) {
		return dsl.fetchExists(
			dsl.selectOne()
				.from(USERS)
				.where(USERS.LOGIN_ID.eq(loginId))
		);
	}

	@Override
	public Optional<Users> findByLoginId(String loginId) {
		return Optional.ofNullable(dsl.selectFrom(USERS)
			.where(USERS.LOGIN_ID.eq(loginId))
			.fetchOneInto(Users.class));
	}

	@Override
	public void updateRefreshToken(String loginId, String refreshToken) {
		dsl.update(USERS)
			.set(USERS.REFRESH_TOKEN, refreshToken)
			.where(USERS.LOGIN_ID.eq(loginId))
			.execute();
	}

	@Override
	public List<Account> findAccounts(Integer userId, String bankCode) {
		return dsl.selectFrom(ACCOUNT)
				.where(ACCOUNT.USER_ID.eq(userId))
				.and(ACCOUNT.BANK_CODE.eq(bankCode))
				.fetchInto(Account.class);
	}

	@Override
	@Transactional
	public Account chooseAccount(Integer userId, String accountNumber) {
		dsl.update(USERS)
				.set(USERS.ACCOUNT_NUMBER, accountNumber)
				.where(USERS.ID.eq(userId))
				.execute();

		return dsl.select(ACCOUNT)
				.from(ACCOUNT)
				.where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber))
				.fetchOneInto(Account.class);
	}

}
