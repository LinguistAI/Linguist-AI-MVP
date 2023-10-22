import { StyleSheet, TextInput } from "react-native";

import Colors from "../theme/colors";

interface PrimaryTextInputProps {
    placeholder: string;
    secureTextEntry: boolean;
}

const PrimaryTextInput = (props: PrimaryTextInputProps) => {


    return (
        <TextInput
            style={styles.input}
            placeholder={props.placeholder}
            secureTextEntry={props.secureTextEntry}
        />
    )
}

const styles = StyleSheet.create({
    input: {
      height: 60,
      margin: 12,
      borderWidth: 1,
      padding: 10,
      borderColor: Colors.gray[300],
      color: Colors.gray[600],
      borderRadius: 16,
    },
  });

export default PrimaryTextInput;