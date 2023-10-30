import { AxiosError } from "axios";

interface CustomError {
  response: {
    data: {
      msg: string;
      status: number;
      timestamp: string;
    };
  };
}

export function isStatusOk(statusCode: number | null | undefined): boolean {
  if (!statusCode) {
    return false;
  }
  return statusCode >= 200 && statusCode < 300;
}

export function isCustomError(error: any): error is CustomError {
  return (
    error.response.data.msg &&
    error.response.data.status &&
    error.response.data.timestamp
  );
}

export function generateErrorResponseMessage(error: any) {
  if (error instanceof AxiosError) {
    switch (error.code) {
      case "ERR_NETWORK":
        return "A network error has occurred. Please check your internet connection and try again.";
    }
  }

  if (isCustomError(error)) {
    return error.response.data.msg;
  }

  return "An unknown error has occurred. Please try again.";
}
