import {createStackNavigator} from '@react-navigation/stack';
import React from 'react';
import {StyleSheet, View} from 'react-native';
import {authNavigations} from '@/constants/navigations';
import {colors} from '@/constants';
import LoginScreen from '@/screens/Auth/LoginScreen';
import LoginHomeScreen from '@/screens/Auth/LoginHomeScreen';
import SignupScreen1 from '@/screens/Auth/SignupScreen1';
import SignupScreen2 from '@/screens/Auth/SignupScreen2';
import Icon from 'react-native-vector-icons/Entypo';

export type AuthStackParamList = {
  [authNavigations.LOGIN_HOME]: undefined;
  [authNavigations.LOGIN]: undefined;
  [authNavigations.SIGNUP1]: undefined;
  [authNavigations.SIGNUP2]: undefined;
};

const Stack = createStackNavigator<AuthStackParamList>();

function AuthStackNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        cardStyle: {
          backgroundColor: colors.WHITE,
        },
        headerStyle: {
          backgroundColor: colors.WHITE,
        },
        headerTitleAlign: 'center',
        headerTitleStyle: {
          fontSize: 15,
        },
        headerTintColor: colors.BLACK,
        headerBackImage: () => {
          return <Icon name="chevron-left" size={24} color={colors.BLACK} />;
        },
      }}>
      <Stack.Screen
        name={authNavigations.LOGIN_HOME}
        component={LoginHomeScreen}
        options={{
          headerTitle: ' ',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name={authNavigations.LOGIN}
        component={LoginScreen}
        options={{
          headerTitle: '로그인',
        }}
      />
      <Stack.Screen
        name={authNavigations.SIGNUP1}
        component={SignupScreen1}
        options={{
          headerTitle: '회원가입',
        }}
      />
      <Stack.Screen
        name={authNavigations.SIGNUP2}
        component={SignupScreen2}
        options={{
          headerTitle: '회원가입',
        }}
      />
    </Stack.Navigator>
  );
}

const styles = StyleSheet.create({});

export default AuthStackNavigator;
