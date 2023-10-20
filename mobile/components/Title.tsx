import { StyleSheet, Text } from "react-native";
import FontSizes from "../theme/fontSizes";

type FontSizeKeys = keyof typeof FontSizes;

interface TitleProps {
  children: React.ReactNode;
  fontSize?: FontSizeKeys;
}

const Title = ({ children, fontSize = "h1" }: TitleProps) => {
  return (
    <Text style={[styles.titleText, { fontSize: FontSizes[fontSize] }]}>
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
