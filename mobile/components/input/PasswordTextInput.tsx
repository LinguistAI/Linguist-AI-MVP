import { Ionicons } from "@expo/vector-icons";
import { useState } from "react";
import ActionIcon from "../ActionIcon";
import PrimaryTextInput, { PrimaryTextInputProps } from "./PrimaryTextInput";

export interface PasswordTextInputProps
  extends Partial<PrimaryTextInputProps> {}

const PasswordTextInput = (props: PasswordTextInputProps) => {
  const [showPassword, setShowPassword] = useState(false);
  const toggleEyePress = () => {
    setShowPassword(!showPassword);
  };
  return (
    <PrimaryTextInput
      name={props.name ? props.name : "password"}
      label={props.label ? props.label : "Password"}
      placeholder={props.placeholder ? props.placeholder : "Password"}
      secureTextEntry={!showPassword}
      rules={
        props.rules
          ? props.rules
          : {
              required: "Password field cannot be left empty!",
            }
      }
      defaultValue={props.defaultValue ? props.defaultValue : ""}
      rightIcon={
        <ActionIcon
          onPress={toggleEyePress}
          icon={
            showPassword ? (
              <Ionicons name="eye-off" size={18} />
            ) : (
              <Ionicons name="eye" size={18} />
            )
          }
        />
      }
    />
  );
};

export default PasswordTextInput;
