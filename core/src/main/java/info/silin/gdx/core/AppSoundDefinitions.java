/*
 * -----------------------------------------------------------------------
 * Copyright 2012 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package info.silin.gdx.core;

import java.util.LinkedList;
import java.util.List;

import com.google.inject.Singleton;
import com.netthreads.libgdx.sound.SoundDefinition;
import com.netthreads.libgdx.sound.SoundDefinitions;

@Singleton
@SuppressWarnings("serial")
public class AppSoundDefinitions implements SoundDefinitions {
	public static final String SOUND_PATH = "sounds";
	public static final String SOUND_PULSE = "clink.wav";

	public static final List<SoundDefinition> SOUNDS = new LinkedList<SoundDefinition>() {
		{
			add(new SoundDefinition(SOUND_PULSE, SOUND_PATH + "/" + SOUND_PULSE));
		}
	};

	@Override
	public List<SoundDefinition> getDefinitions() {
		return SOUNDS;
	}
}
