import { StyleSheet, View } from "react-native";
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

type FormValues = {
  email: string;
  password: string;
};

const LoginScreen = () => {
  const methods = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const onSubmit: SubmitHandler<FormValues> = (data) => {
    console.log(data);
  };

  const onError: SubmitErrorHandler<FormValues> = (errors, e) => {
    return console.log(errors);
  };

  return (
    <View style={styles.container}>
      <FormProvider {...methods}>
        <View style={styles.mainSection}>
          <View>
            <EmailTextInput />
            <PasswordTextInput />
            <PrimaryButton onPress={methods.handleSubmit(onSubmit, onError)}>
              Login
            </PrimaryButton>
          </View>
        </View>
      </FormProvider>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
  },
  mainSection: {
    flex: 5,
    justifyContent: "center",
  },
});

export default LoginScreen;
