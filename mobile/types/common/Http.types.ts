export interface APIResponse<T> {
  data: {
    msg: string;
    status: number;
    timestamp: Date;
    data?: T;
  };
}
