export interface SecondDisplayPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
