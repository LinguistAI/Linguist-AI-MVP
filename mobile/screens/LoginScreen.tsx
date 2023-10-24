import {
  FormProvider,
  SubmitErrorHandler,
  SubmitHandler,
  useForm,
} from "react-hook-form";
import { StyleSheet, Text, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import EmailTextInput from "../components/input/EmailTextInput";
import PasswordTextInput from "../components/input/PasswordTextInput";
import PrimaryTextInput from "../components/input/PrimaryTextInput";
import useNotifications from "../hooks/useNotifications";
import Colors from "../theme/colors";

type FormValues = {
  email: string;
  password: string;
};

interface LoginScreenProps {
  navigation: any;
}

const LoginScreen = (props: LoginScreenProps) => {
  const { add } = useNotifications();
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

  const onError: SubmitErrorHandler<FormValues> = (errors, e) => {};

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
            LOG IN
          </PrimaryButton>
        </View>
      </FormProvider>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 10,
    padding: 20,
  },
  forgotPassword: {
    color: Colors.primary[300],
    textAlign: "center",
    textDecorationLine: "underline",
    fontSize: 16,
  },
  mainSection: {
    flex: 5,
    justifyContent: "flex-start",
    gap: 15,
  },
});

export default LoginScreen;
