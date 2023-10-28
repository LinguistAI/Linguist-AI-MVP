interface LoginDto {
  email: string;
  password: string;
}

interface RegisterDto {
  username: string;
  email: string;
  password: string;
}

interface RRegister {
  username: string;
  email: string;
  id: string;
}

interface RLogin {
  username: string;
  email: string;
  id: string;
  accessToken: string;
  refreshToken: string;
}