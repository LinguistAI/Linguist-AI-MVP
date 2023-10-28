import { AxiosError } from "axios";
import { axiosBase } from "..";
import { APIResponse } from "../../types/common";

export async function login(loginDto: LoginDto) {}

export async function register(registerDto: RegisterDto) {
  try {
    const res = await axiosBase.post<APIResponse<RRegister>>(
      "/auth/register",
      registerDto
    );
    return res;
  } catch (error: any) {
    if (error instanceof AxiosError) {
      return error;
    }
  }
}

export async function changePassword() {}

export async function resetPasswordRequest() {}

export async function resetPasswordConfirm() {}
