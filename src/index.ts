import { registerPlugin } from '@capacitor/core';

import type { SecondDisplayPlugin } from './definitions';

const SecondDisplay = registerPlugin<SecondDisplayPlugin>('SecondDisplay', {
  web: () => import('./web').then((m) => new m.SecondDisplayWeb()),
});

export * from './definitions';
export { SecondDisplay };
