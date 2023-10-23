import { StyleSheet, View, Text } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import {
  useForm,
  FormProvider,
  SubmitHandler,
  SubmitErrorHandler,
} from "react-hook-form";
import EmailTextInput from "../components/input/EmailTextInput";
import Colors from "../theme/colors";

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
          <Text style={styles.hintText}>
            You will receive an email to create a new password if your email
            exists in our system.
          </Text>
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
  hintText: {
    color: Colors.gray[600],
  },
  mainSection: {
    flex: 5,
    justifyContent: "center",
    gap: 15,
  },
});

export default ForgotPasswordScreen;
