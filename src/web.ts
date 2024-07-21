import { WebPlugin } from '@capacitor/core';

import type { ExoPlayerPlugin } from './definitions';

export class ExoPlayerWeb extends WebPlugin implements ExoPlayerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
