import PrimaryTextInput, { PrimaryTextInputProps } from "./PrimaryTextInput";

interface PasswordTextInputProps
  extends Omit<
    PrimaryTextInputProps,
    "name" | "label" | "rules" | "defaultValue"
  > {
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
