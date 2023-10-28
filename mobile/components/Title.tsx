import { StyleSheet, Text } from "react-native";
import TitleSizes from "../theme/fontSizes";

type FontSizeKeys = keyof typeof TitleSizes;

interface TitleProps {
  children: React.ReactNode;
  fontSize?: FontSizeKeys;
}

const Title = ({ children, fontSize = "h1" }: TitleProps) => {
  return (
    <Text style={[styles.titleText, { fontSize: TitleSizes[fontSize] }]}>
      {children}
    </Text>
  );
};

const styles = StyleSheet.create({
  titleText: {
    fontSize: 24,
    fontWeight: "bold",
    marginVertical: 8,
    textAlign: "center",
  },
});

export default Title;
