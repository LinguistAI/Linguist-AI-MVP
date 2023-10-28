import { Button, GestureResponderEvent, Text, View } from "react-native";
import ErrorBoundary from "react-native-error-boundary";

function handleJSErrorForErrorBoundary(error: any, stackTrace: string) {
  console.log(stackTrace, error);
}

const CustomErrorFallbackComponent = (props: {
  error: Error;
  resetError: (event: GestureResponderEvent) => void;
}) => {
  return (
    <View>
      <Text>Something happened!</Text>
      <Text>{props.error.toString()}</Text>
      <Button onPress={props.resetError} title={"Try again"} />
    </View>
  );
};

interface CustomErrorBoundaryProps {
  children: React.ReactElement;
}

export function CustomErrorBoundary({ children }: CustomErrorBoundaryProps) {
  return (
    <ErrorBoundary
      onError={handleJSErrorForErrorBoundary}
      FallbackComponent={CustomErrorFallbackComponent}
    >
      {children}
    </ErrorBoundary>
  );
}
