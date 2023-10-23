import PrimaryTextInput, { PrimaryTextInputProps } from "./PrimaryTextInput";

interface PasswordTextInputProps extends Partial<PrimaryTextInputProps> {
  name?: string;
  label?: string;
  placeholder?: string;
  rules?: any;
  defaultValue?: string;
}

const PasswordTextInput = (props: PasswordTextInputProps) => {
  return (
    <PrimaryTextInput
      name={props.name ? props.name : "password"}
      label={props.label ? props.label : "Password"}
      placeholder={props.placeholder ? props.placeholder : "Password"}
      rules={
        props.rules
          ? props.rules
          : {
              required: "Password is required!",
            }
      }
      defaultValue={props.defaultValue ? props.defaultValue : ""}
    />
  );
};

export default PasswordTextInput;
