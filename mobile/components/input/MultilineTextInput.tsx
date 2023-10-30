import { useState } from "react";
import { StyleSheet, Text, TextInput, TextInputProps } from "react-native";
import Colors from "../../theme/colors";

interface MultilineTextInputProps extends TextInputProps {
  onChangeText: (text: string) => void;
  value: string;
  maxHeight?: number;
}

const MultilineTextInput = (props: MultilineTextInputProps) => {
  const [height, setHeight] = useState(0);

  return (
    <TextInput
      {...props}
      value={props.value}
      multiline={true}
      onChangeText={props.onChangeText}
      onContentSizeChange={(event) =>
        setHeight(event.nativeEvent.contentSize.height)
      }
      style={[
        styles.input,
        {
          height: Math.max(props.maxHeight ?? 35, height),
        },
      ]}
    />
  );
};

const styles = StyleSheet.create({
  input: {
    fontSize: 16,
    color: Colors.gray[900],
    padding: 0,
    margin: 0,
  },
});

export default MultilineTextInput;
