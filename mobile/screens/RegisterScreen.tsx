import { FormProvider, useForm } from "react-hook-form";
import { ScrollView, StyleSheet, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import EmailTextInput from "../components/input/EmailTextInput";
import PasswordTextInput from "../components/input/PasswordTextInput";
import PrimaryTextInput from "../components/input/PrimaryTextInput";
import PasswordInputWithRequirements from "../containers/PasswordInputWithRequirements/PasswordInputWithRequirements";
import { Requirement } from "../containers/PasswordInputWithRequirements/Requirement";
import useNotifications from "../hooks/useNotifications";

type RegisterFormValues = {
  userName: string;
  email: string;
  password: string;
  repeatPassword: string;
};

const RegisterScreen = () => {
  const methods = useForm<RegisterFormValues>({
    defaultValues: {
      userName: "",
      email: "",
      password: "",
      repeatPassword: "",
    },
    mode: "onSubmit",
  });

  const onSubmit = (data: RegisterFormValues) => {};

  const onError = (errors: any, e: any) => {
    if (methods.formState.isValid) {
      console.log("No errors. This should not be called.");
    }
  };

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
    <ScrollView>
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
            rules={{
              required: "Repeating password is required!",
              validate: (value: string) =>
                value === methods.getValues("password") ||
                "Passwords must match!",
            }}
          />
          <PrimaryButton onPress={methods.handleSubmit(onSubmit, onError)}>
            REGISTER
          </PrimaryButton>
        </FormProvider>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 12,
    padding: 20,
    gap: 15,
  },
  errorMessage: {
    color: "red",
  },
});

export default RegisterScreen;
