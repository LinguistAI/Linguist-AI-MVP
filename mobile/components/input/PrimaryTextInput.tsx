import {
  StyleSheet,
  Text,
  TextInput,
  TextInputProps,
  View,
} from "react-native";

import { ErrorMessage } from "@hookform/error-message";
import { useController, useFormContext } from "react-hook-form";
import Colors from "../../theme/colors";

export interface PrimaryTextInputProps extends TextInputProps {
  name: string;
  label: string;
  rules: Object;
  defaultValue: string;
  rightIcon?: React.ReactNode;
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
  const { field, formState } = useController({
    name: props.name,
    rules: props.rules,
    defaultValue: props.defaultValue,
  });

  // const getErrorBorder = () => {
  //   const errorKeyNames = Object.keys(formState.errors);
  //   if (formState.isSubmitted && !formState.isDirty && !formState.isValid) {
  //     return styles.errorBorder;
  //   }
  // };

  return (
    <View>
      {props.label && <Text style={styles.label}>{props.label}</Text>}
      <View style={styles.inputRoot}>
        <View style={[styles.inputContainer]}>
          <TextInput
            onChangeText={field.onChange}
            onBlur={field.onBlur}
            value={field.value}
            {...props}
          />
          {props.rightIcon}
        </View>
      </View>
      <ErrorMessage
        name={props.name}
        render={({ message }) => (
          <Text style={styles.errorMessage}>{message}</Text>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  label: {
    fontSize: 14,
    color: Colors.gray[900],
    fontWeight: "bold",
    marginBottom: 6,
  },
  inputContainer: {
    flex: 1,
    flexDirection: "row",
    alignItems: "center",
    alignContent: "center",
    justifyContent: "space-between",
    backgroundColor: "#f3f3f3",
    borderRadius: 8,
    paddingHorizontal: 8,
  },
  inputRoot: {
    flex: 1,
    height: 60,
    borderWidth: 1,
    padding: 10,
    borderColor: Colors.gray[400],
    color: Colors.gray[600],
    borderRadius: 8,
  },
  errorMessage: {
    color: "red",
    marginLeft: 10,
    marginTop: 5,
  },
  errorBorder: {
    borderColor: Colors.red[500],
  },
});

export default PrimaryTextInput;
