export interface Model {
  id: bigint;
  title: string;
  description: string;
  source: string;
  uploadedAt: bigint;
  tag: string;
  fileName: string;
  contentType: string;
  size: bigint;
  downloads: bigint;
}
