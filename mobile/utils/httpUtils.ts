export function isStatusOk(statusCode: number | null | undefined): boolean {
  if (!statusCode) {
    return false;
  }
  return statusCode >= 200 && statusCode < 300;
}
