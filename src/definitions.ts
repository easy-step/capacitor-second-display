export interface SecondDisplayPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  showOnSecondScreen(options: { url: string }): Promise<void>;
}
