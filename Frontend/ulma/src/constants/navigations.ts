const homeNavigations = {
  LANDING: 'Landing',
  SCHEDULE_MAIN: 'ScheduleMain',
  SCHEDULE_ADD: 'ScheduleAdd',
  SELECT_FRIEND: 'SelectFriend',
  FRIENDS_DETAIL: 'FriendsDetail',
  IMAGE_OCR: 'ImageOCR',
} as const;

const authNavigations = {
  AUTH_HOME: 'AuthHome',
  LOGIN_HOME: 'LoginHome',
  LOGIN: 'Login',
  SIGNUP1: 'Signup1',
  SIGNUP2: 'Signup2',
} as const;

const eventNavigations = {
  EVENT_ADD: 'EventAdd',
  EVENT_DATE: 'EventDate',
  EVENT: 'Event',
  EVENT_COMMENT: 'EventComment',
  EVENT_COMMENT_RESULT: 'EventCommentResult',
  EVENT_DETAIL: 'EVENT_DETAIL',
  AI_RECOMMEND_MESSAGE: 'AIRecommendMessage',
  EVENT_FIX: 'EVENT_FIX',
  ACCOUNT_HISTORY: 'AccountHistory',
  FRIEND_SEARCH: 'Friendsearch',
  EVENT_EXCEL: 'ExcelScreen',
} as const;

const payNavigations = {
  HOME: 'PayHome',
  ACCOUNT_INPUT: 'Accountinput',
  ADD_HISTORY: 'Addhistory',
  CHARGER_RESULT: 'Chargerresult',
  PAY_LIST: 'Paylist',
  PAY_RECHARGE: 'Payrecharge',
  FRIENDHSHIP_SECLECT: 'Friendshipselect',
  RECOMMEND_OPTION: 'RecommendOption',
  SENDING: 'Sending',
  SEND_ACCOUNT: 'SendAccount',
  SEND_RESULT: 'Sendresult',
  INPUT_AMOUNT: 'InputAmount',
  ACCOUNT_INFO: 'AccountInfo',
  ADD_ACCOUNT: 'AddAccount',
  ACCOUNT_VERIFY: 'AccountVerify',
  ACCOUNT_DETAIL: 'AccountDetail',
  EXCEL_SCREEN: 'ExcelScreen',
  TFA: 'TFA',
} as const;

const mypageNavigations = {
  MYPAGE_HOME: 'MyPageHome',
  USER_DETAIL: 'UserDetail',
} as const;

const friendsNavigations = {
  FRIENDS_LIST: 'FriendsList',
  FRIENDS_HOME: 'FriendsHome',
  FRIENDS_ADD: 'FriendsAdd',
  FREINDS_DETAIL: 'FriendsDetail',
} as const;

export {
  homeNavigations,
  authNavigations,
  eventNavigations,
  payNavigations,
  mypageNavigations,
  friendsNavigations,
};
