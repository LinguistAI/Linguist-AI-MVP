import AsyncStorage from "@react-native-async-storage/async-storage";
import * as SecureStore from "expo-secure-store";
import { atom, useAtom } from "jotai";
import { StoredUserInfoWithTokens } from "../../types/auth";

const emptyUserAtom = {
  username: "",
  email: "",
  accessToken: "",
  refreshToken: "",
};

const userAtom = atom<StoredUserInfoWithTokens>(emptyUserAtom);

const useUser = () => {
  const [user, setUser] = useAtom(userAtom);

  const storeUserDetails = async (details: StoredUserInfoWithTokens) => {
    try {
      await SecureStore.setItemAsync("user", JSON.stringify(details));
      setUser(details);
    } catch (error) {
      // Handle error
      console.error("Error storing user details: ", error);
    }
  };

  const getUserDetails = async () => {
    try {
      const userDetails = await SecureStore.getItemAsync("user");
      if (userDetails !== null) {
        setUser(JSON.parse(userDetails));
      }
    } catch (error) {
      // Handle error
      console.error("Error retrieving user details: ", error);
    }
  };

  const clearUserDetails = async () => {
    try {
      await SecureStore.deleteItemAsync("user");
      setUser(emptyUserAtom);
    } catch (error) {
      // Handle error
      console.error("Error clearing user details: ", error);
    }
  };

  return {
    user,
    storeUserDetails,
    getUserDetails,
    clearUserDetails,
  };
};

export default useUser;
