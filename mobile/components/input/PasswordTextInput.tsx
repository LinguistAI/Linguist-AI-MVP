import PrimaryTextInput, { PrimaryTextInputProps } from "./PrimaryTextInput";

export interface PasswordTextInputProps
  extends Partial<PrimaryTextInputProps> {}

const PasswordTextInput = (props: PasswordTextInputProps) => {
  return (
    <PrimaryTextInput
      name={props.name ? props.name : "password"}
      label={props.label ? props.label : "Password"}
      placeholder={props.placeholder ? props.placeholder : "Password"}
      secureTextEntry={true}
      rules={
        props.rules
          ? props.rules
          : {
              required: "Password field cannot be left empty!",
            }
      }
      defaultValue={props.defaultValue ? props.defaultValue : ""}
    />
  );
};

export default PasswordTextInput;
