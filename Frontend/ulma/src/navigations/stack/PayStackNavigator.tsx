import {payNavigations} from '@/constants/navigations';
import {createStackNavigator} from '@react-navigation/stack';
import React from 'react';
import {StyleSheet, Text} from 'react-native';

import {colors} from '@/constants';
import Icon from 'react-native-vector-icons/Entypo';

import AccountinputScreen from '@/screens/Pay/AccountinputScreen';
import AddhistoryScreen from '@/screens/Pay/AddhistoryScreen';
import ChangeresultScreen from '@/screens/Pay/ChargeresultScreen';
import FriendshipselectScreen from '@/screens/Pay/FriendshipselectScreen';
import SendresultScreen from '@/screens/Pay/SendresultScreen';
import PaylistScreen from '@/screens/Pay/PaylistScreen';
import PayrechargingScreen from '@/screens/Pay/PayrechargingScreen';
import RecommendOptionScreen from '@/screens/Pay/RecommendOptionScreen';
import AccounthistoryScreen from '@/screens/Events/AccounthistoryScreen';
import FriendsearchScreen from '@/screens/Events/FriendsearchScreen';
import InputAmountScreen from '@/screens/Pay/InputAmountScreen';
import AiRecommendScreen from '@/screens/Pay/AiRecommendScreen'; // '.1'을 제거한 경로
import PayHomeScreen from '@/screens/Pay/PayHomeScreen';
import AccountInfoScreen from '@/screens/Pay/AccountInfoScreen';
import AddAccountScreen from '@/screens/Pay/AddAccountScreen';
import SendAccountScreen from '@/screens/Pay/SendAccountScreen';
import AccountVerifyScreen from '@/screens/Pay/AccountVerifyScreen';
import AccountDetailScreen from '@/screens/Pay/AccountDetailScreen';
import ExcelScreen from '@/screens/Events/ExcelScreen';
import {DocumentPickerResponse} from 'react-native-document-picker';
import SendingScreen from '@/screens/Pay/SendingScreen';
import {StackScreenProps} from '@react-navigation/stack';
import TFAScreen from '@/screens/Pay/TFAScreen';

export type payStackParamList = {
  [payNavigations.HOME]: undefined;
  [payNavigations.ACCOUNT_INPUT]: undefined;
  [payNavigations.ADD_HISTORY]: undefined;
  [payNavigations.CHARGER_RESULT]: undefined;
  [payNavigations.FRIENDHSHIP_SECLECT]: undefined;
  [payNavigations.PAY_LIST]: undefined;
  [payNavigations.PAY_RECHARGE]: undefined;
  [payNavigations.RECOMMEND_OPTION]: undefined;
  [payNavigations.SENDING]: {targetAccountNumber: string};
  [payNavigations.SEND_RESULT]: undefined;
  [payNavigations.INPUT_AMOUNT]: {guestId: number};
  [payNavigations.ACCOUNT_INFO]: undefined;
  [payNavigations.ADD_ACCOUNT]: undefined;
  [payNavigations.SEND_ACCOUNT]: undefined;
  [payNavigations.ACCOUNT_VERIFY]: {verifyNumber: number};
  [payNavigations.ACCOUNT_DETAIL]: {accountNumber: string; bankCode: string};
  [payNavigations.EXCEL_SCREEN]: {file?: DocumentPickerResponse};
  [payNavigations.TFA]: undefined;
};

const Stack = createStackNavigator<payStackParamList>();

function PayStackNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        cardStyle: {
          backgroundColor: colors.WHITE,
        },
        headerStyle: {
          backgroundColor: colors.WHITE,
        },
        headerTitleAlign: 'left',
        headerTitleStyle: {
          fontSize: 18,
          fontWeight: 'bold',
        },
        headerTintColor: colors.BLACK,
      }}>
      <Stack.Screen
        name={payNavigations.HOME}
        component={PayHomeScreen}
        options={{
          headerTitle: () => (
            <Text style={{fontSize: 18, fontWeight: 'bold'}}>
              <Text style={{color: colors.GREEN_700}}>ULMA</Text>
              <Text style={{color: colors.BLACK}}> PAY</Text>
            </Text>
          ),
        }}
      />
      <Stack.Screen
        name={payNavigations.ADD_HISTORY}
        component={AddhistoryScreen}
        options={{
          headerTitle: '이벤트 자세히 보기',
          headerStyle: {backgroundColor: colors.LIGHTGRAY},
        }}
      />
      <Stack.Screen
        name={payNavigations.INPUT_AMOUNT}
        component={InputAmountScreen}
        options={{
          headerTitle: '',
        }}
      />
      <Stack.Screen
        name={payNavigations.ACCOUNT_INPUT}
        component={AccountinputScreen}
        options={{
          headerTitle: '계좌 입력',
          headerBackImage: () => {
            return <Icon name="cross" size={24} color={colors.BLACK} />;
          },
        }}
      />
      <Stack.Screen
        name={payNavigations.SEND_RESULT}
        component={SendresultScreen}
        options={{
          headerTitle: '송금 결과',
          headerBackImage: () => {
            return <Icon name="cross" size={24} color={colors.BLACK} />;
          },
        }}
      />
      <Stack.Screen
        name={payNavigations.CHARGER_RESULT}
        component={ChangeresultScreen}
        options={{
          headerTitle: 'Pay 충전 결과',
        }}
      />
      <Stack.Screen
        name={payNavigations.FRIENDHSHIP_SECLECT}
        component={FriendshipselectScreen}
        options={{
          headerTitle: 'AI 금액 추천',
        }}
      />
      <Stack.Screen
        name={payNavigations.PAY_LIST}
        component={PaylistScreen}
        options={{
          headerTitle: 'Pay 이력보기',
        }}
      />
      <Stack.Screen
        name={payNavigations.PAY_RECHARGE}
        component={PayrechargingScreen}
        options={{
          headerTitle: 'Pay 충전하기',
          headerBackImage: () => {
            return <Icon name="cross" size={24} color={colors.BLACK} />;
          },
        }}
      />
      <Stack.Screen
        name={payNavigations.SENDING}
        component={SendingScreen}
        options={{
          headerTitle: '송금하기',
        }}
      />
      <Stack.Screen
        name={payNavigations.RECOMMEND_OPTION}
        component={AiRecommendScreen}
        options={{
          headerTitle: 'AI 금액 추천',
        }}
      />
      <Stack.Screen
        name={payNavigations.ACCOUNT_INFO}
        component={AccountInfoScreen}
        options={{
          headerTitle: '계좌 정보 보기',
        }}
      />
      <Stack.Screen
        name={payNavigations.ADD_ACCOUNT}
        component={AddAccountScreen}
        options={{
          headerTitle: '연결 계좌 추가하기',
        }}
      />
      <Stack.Screen
        name={payNavigations.SEND_ACCOUNT}
        component={SendAccountScreen}
        options={{
          headerTitle: '송금하기',
        }}
      />
      <Stack.Screen
        name={payNavigations.ACCOUNT_VERIFY}
        component={props => <AccountVerifyScreen {...props} />}
        options={{
          headerTitle: '송금하기',
        }}
      />
      <Stack.Screen
        name={payNavigations.ACCOUNT_DETAIL}
        component={props => <AccountDetailScreen {...props} />}
        options={{
          headerTitle: '계좌 상세 보기',
        }}
      />
      <Stack.Screen
        name={payNavigations.EXCEL_SCREEN}
        component={props => <ExcelScreen {...props} />}
        options={{
          headerTitle: '엑셀 파일 등록',
          headerStyle: {backgroundColor: colors.LIGHTGRAY},
        }}
      />
      <Stack.Screen
        name={payNavigations.TFA}
        component={TFAScreen}
        options={{
          headerTitle: '2차 비밀번호 입력',
        }}
      />
    </Stack.Navigator>
  );
}

const styles = StyleSheet.create({});

export default PayStackNavigator;
