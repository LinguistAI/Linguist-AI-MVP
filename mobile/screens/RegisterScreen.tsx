import { FormProvider, useForm } from "react-hook-form";
import { StyleSheet, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import EmailTextInput from "../components/input/EmailTextInput";
import PasswordTextInput from "../components/input/PasswordTextInput";
import PrimaryTextInput from "../components/input/PrimaryTextInput";
import PasswordInputWithRequirements, {
  Requirement,
} from "../containers/PasswordInputWithRequirements";

type FormValues = {
  userName: string;
  email: string;
  password: string;
  repeatPassword: string;
};

const RegisterScreen = () => {
  const methods = useForm({
    defaultValues: {
      userName: "",
      email: "",
      password: "",
      repeatPassword: "",
    },
  });

  const passwordRequirements: Requirement[] = [
    {
      re: /^.{8,}$/,
      label: "Must be at least 8 characters long.",
    },
    {
      re: /[A-Z]/,
      label: "Must contain at least 1 uppercase letter.",
    },
    {
      re: /[0-9]/,
      label: "Must contain at least 1 number.",
    },
    {
      re: /[^A-Za-z0-9]/,
      label: "Must contain at least 1 special character.",
    },
  ];

  return (
    <View style={styles.container}>
      <FormProvider {...methods}>
        <PrimaryTextInput
          defaultValue=""
          name="userName"
          rules={{
            required: "Username is required!",
            pattern: {
              value: /^.{3,}$/,
              message: "Username must be at least 3 characters long!",
            },
          }}
          label="Username"
          placeholder="Username"
        />
        <EmailTextInput name="email" />
        <PasswordInputWithRequirements
          requirements={passwordRequirements}
          name="password"
          label="Password"
          placeholder="Password"
        />
        <PasswordTextInput
          placeholder="Repeat password"
          label="Repeat password"
          name="repeatPassword"
        />
      </FormProvider>
      <PrimaryButton>REGISTER</PrimaryButton>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
    gap: 20,
  },
});

export default RegisterScreen;
