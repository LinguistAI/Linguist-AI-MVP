import { Ionicons } from "@expo/vector-icons";
import { FormProvider, useForm, useFormContext } from "react-hook-form";
import { StyleSheet, Text, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import PasswordTextInput, {
  PasswordTextInputProps,
} from "../components/input/PasswordTextInput";

export type Requirement = {
  re: RegExp;
  label: string;
};

interface RequirementProps {
  meets: boolean;
  text: string;
}

const PasswordRequirement = (props: RequirementProps) => {
  const { meets, text } = props;

  return (
    <View>
      <Text
        style={[styles.requirementText, meets ? styles.meets : styles.fails]}
      >
        {meets ? (
          <Ionicons name="checkmark-circle" size={14} color="green" />
        ) : (
          <Ionicons name="close-circle" size={14} color="red" />
        )}
        {text}
      </Text>
    </View>
  );
};

interface PasswordInputWithRequirementsProps extends PasswordTextInputProps {
  requirements: Requirement[];
}

const PasswordInputWithRequirements = (
  props: PasswordInputWithRequirementsProps
) => {
  const { requirements } = props;
  const formContext = useFormContext();
  const password = formContext.watch("password");

  const requirementsFeedbackDisplay = requirements.map((requirement) => (
    <PasswordRequirement
      text={requirement.label}
      meets={requirement.re.test(password)}
    />
  ));

  return (
    <View style={styles.container}>
      <PasswordTextInput {...props} />
      <View style={styles.requirements}>{requirementsFeedbackDisplay}</View>
    </View>
  );
};

const styles = StyleSheet.create({
  requirementText: {
    fontSize: 16,
  },
  container: {
    gap: 10,
  },
  requirements: {
    gap: 6,
  },
  meets: {
    color: "green",
  },
  fails: {
    color: "red",
  },
});

export default PasswordInputWithRequirements;
