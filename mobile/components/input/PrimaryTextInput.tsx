import {
  StyleSheet,
  Text,
  TextInput,
  TextInputProps,
  View,
} from "react-native";

import { useController, useFormContext } from "react-hook-form";
import Colors from "../../theme/colors";

export interface PrimaryTextInputProps extends TextInputProps {
  name: string;
  label: string;
  rules: Object;
  defaultValue: string;
}

const PrimaryTextInput = (props: PrimaryTextInputProps) => {
  const { name } = props;

  const formContext = useFormContext();

  if (!formContext || !name) {
    const msg = !formContext
      ? "TextInput must be wrapped by the FormProvider"
      : "Name must be defined";
    console.error(msg);
    return null;
  }

  return <ControlledInput {...props} />;
};

const ControlledInput = (props: PrimaryTextInputProps) => {
  const { field } = useController({
    name: props.name,
    rules: props.rules,
    defaultValue: props.defaultValue,
  });

  return (
    <View>
      {props.label && <Text style={styles.label}>{props.label}</Text>}
      <View>
        <TextInput
          style={styles.input}
          onChangeText={field.onChange}
          onBlur={field.onBlur}
          value={field.value}
          {...props}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  label: {
    fontSize: 14,
    color: Colors.gray[900],
    fontWeight: "bold",
    marginBottom: 8,
  },
  input: {
    height: 60,
    borderWidth: 1,
    padding: 10,
    borderColor: Colors.gray[300],
    color: Colors.gray[600],
    borderRadius: 8,
  },
});

export default PrimaryTextInput;
