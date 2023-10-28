import axios from "axios";
import * as SecureStorage from "expo-secure-store";
import { StoredUserInfoWithTokens } from "../types/auth";

const decideBackendURL = (): string => {
  if (process.env.NODE_ENV === "production") {
    return process.env.EXPO_PUBLIC_API_URL as string;
  } else if (process.env.NODE_ENV === "development") {
    return process.env.EXPO_PUBLIC_LOCAL_API_URL as string;
  }

  return "";
};

export const axiosBase = axios.create({
  baseURL: decideBackendURL(),
  headers: { "Content-Type": "application/json" },
});

export const axiosSecure = axios.create({
  baseURL: decideBackendURL(),
  headers: { "Content-Type": "application/json" },
  withCredentials: true,
});

axiosSecure.interceptors.request.use(
  async (config) => {
    const user = (await SecureStorage.getItemAsync(
      "user"
    )) as unknown as StoredUserInfoWithTokens;
    if (!config?.headers!["Authorization"]) {
      config.headers!["Authorization"] = `Bearer ${user.accessToken}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

axiosSecure.interceptors.response.use(
  (response) => response,
  async (error) => {
    const prevRequest = error?.config;
    if (error?.response?.status === 403 && !prevRequest?.sent) {
      prevRequest.sent = true;
      const user = (await SecureStorage.getItemAsync(
        "user"
      )) as unknown as StoredUserInfoWithTokens;
      const res = await axios.get<{ accessToken: string }>("/auth/refresh", {
        withCredentials: true,
        headers: {
          Authorization: `Bearer ${user.refreshToken}`,
        },
      });
      user.accessToken = res.data.accessToken;
      SecureStorage.setItemAsync("user", JSON.stringify(user));
      prevRequest.headers["Authorization"] = `Bearer ${res.data.accessToken}`;
      return axiosSecure(prevRequest);
    }
    return Promise.reject(error);
  }
);
