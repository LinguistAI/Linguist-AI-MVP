export interface APIResponse<T> {
  data?: T;
  msg: string;
  status: number;
  timestamp: Date;
}
