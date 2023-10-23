import { StyleSheet, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import {
  useForm,
  FormProvider,
  SubmitHandler,
  SubmitErrorHandler,
} from "react-hook-form";
import EmailTextInput from "../components/input/EmailTextInput";

type FormValues = {
  email: string;
};

const ForgotPasswordScreen = () => {
  const methods = useForm({
    defaultValues: {
      email: "",
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
          <EmailTextInput />
          <PrimaryButton onPress={methods.handleSubmit(onSubmit, onError)}>
            Reset Password
          </PrimaryButton>
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

export default ForgotPasswordScreen;
