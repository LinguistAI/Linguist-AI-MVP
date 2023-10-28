export interface StoredUserInfo {
  email: string;
  username: string;
}

export interface StoredUserInfoWithTokens extends StoredUserInfo {
  accessToken: string;
  refreshToken: string;
}
