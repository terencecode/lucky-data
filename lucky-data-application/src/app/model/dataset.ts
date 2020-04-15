export class Dataset {
  id: bigint;
  title: string;
  description: string;
  source: string;
  uploadedAt: bigint;
  date: bigint;
  startDate: bigint;
  endDate: bigint;
  latitude: number;
  longitude: number;
  tag: string;
  fileName: string;
  contentType: string;
  size: bigint;
  downloads: bigint;
}
