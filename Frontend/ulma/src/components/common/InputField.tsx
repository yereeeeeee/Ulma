import React, {ForwardedRef, forwardRef, useRef, useState} from 'react';
import {
  Dimensions,
  Pressable,
  StyleSheet,
  TextInput,
  TextInputProps,
  View,
} from 'react-native';

import {colors} from '@/constants';

interface InputFieldProps extends TextInputProps {
  disabled?: boolean;
}

const deviceHeight = Dimensions.get('screen').height;

const InputField = forwardRef(
  ({disabled = false, ...props}: InputFieldProps) => {
    const innerRef = useRef<TextInput | null>(null);
    const [text, onChangeText] = useState(''); // useState 컴포넌트 내부로 이동

    const handlePressInput = () => {
      innerRef.current?.focus();
    };

    return (
      <Pressable onPress={handlePressInput}>
        <View style={styles.container}>
          <TextInput
            ref={innerRef}
            style={[styles.input, disabled && styles.disabled]}
            placeholderTextColor={colors.GRAY}
            value={text}
            onChangeText={onChangeText}
            spellCheck={false}
            autoCorrect={false}
            {...props}
          />
        </View>
      </Pressable>
    );
  },
);

const styles = StyleSheet.create({
  container: {
    borderWidth: 1,
    borderColor: colors.GREEN_700,
    height: 40,
  },
  input: {
    color: colors.BLACK,
    backgroundColor: colors.WHITE,
    padding: 10,
    fontSize: 15,
  },
  disabled: {
    backgroundColor: colors.GRAY,
    color: colors.GRAY,
  },
});

export default InputField;
