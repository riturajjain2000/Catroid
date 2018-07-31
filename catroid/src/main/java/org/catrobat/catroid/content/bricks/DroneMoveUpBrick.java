/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.bricks;

import android.content.Context;
import android.view.View;

import org.catrobat.catroid.R;
import org.catrobat.catroid.common.BrickValues;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.actions.ScriptSequenceAction;
import org.catrobat.catroid.formulaeditor.Formula;

import java.util.List;

public class DroneMoveUpBrick extends FormulaBrick {

	private static final long serialVersionUID = 1L;

	public DroneMoveUpBrick() {
		this(new Formula(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS / 1000.0),
				new Formula(BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
	}

	public DroneMoveUpBrick(int durationInMilliseconds, int powerInPercent) {
		this(new Formula(durationInMilliseconds / 1000.0), new Formula(powerInPercent));
	}

	public DroneMoveUpBrick(Formula durationInSeconds, Formula powerInPercent) {
		addAllowedBrickField(BrickField.DRONE_TIME_TO_FLY_IN_SECONDS, R.id.brick_drone_move_up_edit_text_second);
		addAllowedBrickField(BrickField.DRONE_POWER_IN_PERCENT, R.id.brick_drone_move_up_edit_text_power);
		setFormulaWithBrickField(BrickField.DRONE_TIME_TO_FLY_IN_SECONDS, durationInSeconds);
		setFormulaWithBrickField(BrickField.DRONE_POWER_IN_PERCENT, powerInPercent);
	}

	@Override
	public int getViewResource() {
		return R.layout.brick_drone_move_up;
	}

	@Override
	public View getPrototypeView(Context context) {
		View prototypeView = super.getPrototypeView(context);
		setSecondsLabel(prototypeView, BrickField.DRONE_TIME_TO_FLY_IN_SECONDS);
		return prototypeView;
	}

	@Override
	public View getView(Context context) {
		super.getView(context);
		setSecondsLabel(view, BrickField.DRONE_TIME_TO_FLY_IN_SECONDS);
		return view;
	}

	@Override
	public int getRequiredResources() {
		return super.getRequiredResources() | Brick.ARDRONE_SUPPORT;
	}

	@Override
	public List<ScriptSequenceAction> addActionToSequence(Sprite sprite, ScriptSequenceAction sequence) {
		sequence.addAction(sprite.getActionFactory().createDroneMoveUpAction(sprite,
				getFormulaWithBrickField(BrickField.DRONE_TIME_TO_FLY_IN_SECONDS),
				getFormulaWithBrickField(BrickField.DRONE_POWER_IN_PERCENT)));
		return null;
	}
}
