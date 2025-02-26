import React, {useState} from 'react';
import {StyleSheet, View, Alert} from 'react-native';
import CustomButton from '@/components/common/CustomButton';
import InputField from '@/components/common/InputField';
import TitleTextField from '@/components/common/TitleTextField';
import useAuthStore from '@/store/useAuthStore';
import axios, {AxiosError} from 'axios';
import Toast from 'react-native-toast-message';

function LoginScreen() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const login = useAuthStore(state => state.login);

  const handleLogin = async () => {
    try {
      const response = await login(loginId, password);
      // Alert.alert('로그인 성공', response.msg);
      // 여기에 로그인 성공 후 네비게이션 로직을 추가할 수 있습니다.
      // 왜 여기 navigate 없는데 로그인하면 이동이 되는걸까?
    } catch (error) {
      const axiosError = error as AxiosError;
      if (axiosError.response && axiosError.response.status === 401) {
        Toast.show({
          text1: '아이디 또는 비밀번호가 올바르지 않습니다.',
          type: 'error',
        });
      } else {
        Toast.show({
          text1: '로그인 중 문제가 발생했습니다. 다시 시도해주세요.',
          type: 'error',
        });
      }
    }
  };

  return (
    <View style={styles.container}>
      <TitleTextField frontLabel="아이디" />
      <InputField
        value={loginId}
        onChangeText={setLoginId}
        placeholder="아이디를 입력해주세요"
        autoCapitalize="none"
      />
      <View style={{height: 20}} />
      <TitleTextField frontLabel="비밀번호" />
      <InputField
        value={password}
        onChangeText={setPassword}
        placeholder="비밀번호를 입력해주세요"
        secureTextEntry
      />
      <CustomButton
        size="maxSize"
        label="로그인"
        posY={30}
        onPress={handleLogin}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    gap: 20,
  },
});

export default LoginScreen;
