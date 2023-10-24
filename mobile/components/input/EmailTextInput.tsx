import PrimaryTextInput, { PrimaryTextInputProps } from "./PrimaryTextInput";

interface EmailTextInputProps extends Partial<PrimaryTextInputProps> {}

const EmailTextInput = (props: EmailTextInputProps) => {
  return (
    <PrimaryTextInput
      name={props.name ? props.name : "email"}
      label={props.label ? props.label : "Email"}
      placeholder={props.placeholder ? props.placeholder : "Email"}
      keyboardType="email-address"
      rules={
        props.rules
          ? props.rules
          : {
              required: "Email is required!",
              pattern: {
                value: /\b[\w\\.+-]+@[\w\\.-]+\.\w{2,4}\b/,
                message: "Must be formatted: name@email.com",
              },
            }
      }
      defaultValue={props.defaultValue ? props.defaultValue : ""}
    />
  );
};

export default EmailTextInput;
