import { WebPlugin } from '@capacitor/core';

import type { SecondDisplayPlugin } from './definitions';

export class SecondDisplayWeb extends WebPlugin implements SecondDisplayPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
