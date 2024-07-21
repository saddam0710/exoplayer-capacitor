export interface ExoPlayerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
