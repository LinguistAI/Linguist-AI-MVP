import { StyleSheet, View, Text } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import PrimaryTextInput from "../components/input/PrimaryTextInput";
import {
  useForm,
  FormProvider,
  SubmitHandler,
  SubmitErrorHandler,
} from "react-hook-form";
import PasswordTextInput from "../components/input/PasswordTextInput";
import EmailTextInput from "../components/input/EmailTextInput";
import Colors from "../theme/colors";

type FormValues = {
  email: string;
  password: string;
};

interface LoginScreenProps {
  navigation: any;
}

const LoginScreen = (props: LoginScreenProps) => {
  const methods = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const onForgotPassword = () => {
    props.navigation.navigate("Forgot Password");
  };

  const onSubmit: SubmitHandler<FormValues> = (data) => {
    console.log(data);
    props.navigation.navigate("Main");
  };

  const onError: SubmitErrorHandler<FormValues> = (errors, e) => {
    return console.log(errors);
  };

  return (
    <View style={styles.container}>
      <FormProvider {...methods}>
        <View style={styles.mainSection}>
          <EmailTextInput />
          <PasswordTextInput />
          <Text style={styles.forgotPassword} onPress={onForgotPassword}>
            Forgot Password?
          </Text>
          <PrimaryButton onPress={methods.handleSubmit(onSubmit, onError)}>
            Login
          </PrimaryButton>
        </View>
      </FormProvider>
    </View>
  );
};

const styles = StyleSheet.create({
  forgotPassword: {
    color: Colors.primary[300],
    textAlign: "center",
    textDecorationLine: "underline",
  },
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
  },
  mainSection: {
    flex: 5,
    justifyContent: "center",
    gap: 15,
  },
});

export default LoginScreen;
