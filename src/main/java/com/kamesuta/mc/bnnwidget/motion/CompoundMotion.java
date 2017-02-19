package com.kamesuta.mc.bnnwidget.motion;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

/**
 * モーションセット
 *
 * @author TeamFruit
 */
public class CompoundMotion implements IMotion, ICompoundMotion {
	/**
	 * 一時停止中の場合true
	 */
	protected boolean paused = true;
	/**
	 * モーションタスク
	 */
	protected @Nonnull TaskList<IMotion> tasks;
	/**
	 * 現在のモーション
	 */
	protected @Nullable IMotion current;
	/**
	 * 最初の値
	 */
	protected float firstcoord;
	/**
	 * 現在の値
	 */
	protected float coord;
	/**
	 * ループの最後
	 */
	protected boolean looplast;
	/**
	 * アニメーション後のタスク
	 */
	protected @Nullable Runnable after;
	protected boolean usecoord;

	public CompoundMotion() {
		this.tasks = new TaskList<IMotion>();
	}

	public CompoundMotion(final float coord) {
		this();
		this.firstcoord = coord;
		this.usecoord = true;
		this.coord = coord;
	}

	@Override
	public @Nonnull CompoundMotion add(final @Nonnull IMotion animation) {
		this.tasks.add(animation);
		return this;
	}

	@Override
	public @Nonnull CompoundMotion setLoop(final boolean b) {
		this.looplast = b;
		return this;
	}

	/**
	 * 現在のタスクを設定
	 * @param current 現在のタスク
	 */
	protected void setCurrent(final @Nullable IMotion current) {
		if (this.current!=null)
			this.current.onFinished();
		this.current = current;
	}

	@Override
	public @Nonnull CompoundMotion stopFirst() {
		this.tasks.finish();
		setCurrent(null);
		return this;
	}

	@Override
	public @Nonnull CompoundMotion stop() {
		this.coord = get();
		stopFirst();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion stopLast() {
		this.coord = getLast();
		stopFirst();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion pause() {
		this.paused = true;
		if (this.current!=null)
			this.current.pause();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion start() {
		this.paused = false;
		if (this.current!=null)
			this.current.resume();
		return this;
	}

	/**
	 * 次のタスクへ進む
	 * @return
	 */
	protected @Nullable IMotion nextCurrent() {
		final IMotion m = this.tasks.poll();
		setCurrent(m);
		return m;
	}

	@Override
	public @Nonnull CompoundMotion next() {
		nextCurrent();
		start();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion stopNext() {
		final boolean notfinish = this.current!=null&&!this.current.isFinished();
		if (!notfinish) {
			if (!this.paused) {
				if (this.current!=null)
					this.coord = this.current.getEnd(this.coord);
				next();
			}
			if (this.looplast&&this.tasks.isFinished())
				restart();
		}
		return this;
	}

	@Override
	public @Nullable IMotion getAnimation() {
		stopNext();
		return this.current;
	}

	@Override
	public @Nullable IMotion getAnimationLast() {
		return this.tasks.getLast();
	}

	@Override
	public float get() {
		return get(this.coord);
	}

	@Override
	public float get(final float start) {
		final IMotion a = getAnimation();
		if (!this.usecoord) {
			this.usecoord = true;
			this.firstcoord = start;
			this.coord = start;
		}
		if (a!=null)
			return a.get(this.coord);
		else
			return this.coord;
	}

	@Override
	public float getLast() {
		final IMotion a = this.tasks.getLast();
		if (a!=null)
			return a.getEnd(this.coord);
		else
			return this.coord;
	}

	@Override
	public boolean isFinished() {
		stopNext();
		final IMotion last = this.tasks.getLast();
		final boolean notfinish = last!=null&&!last.isFinished();
		return !this.looplast&&!notfinish&&this.tasks.isLast();
	}

	@Override
	public @Nonnull CompoundMotion restart() {
		for (final IMotion m : this.tasks)
			m.pause().restart();
		this.coord = this.firstcoord;
		this.tasks.restart();
		next();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion reset() {
		this.tasks.reset();
		this.coord = this.firstcoord;
		setCurrent(null);
		return this;
	}

	@Override
	public @Nonnull CompoundMotion finish() {
		stopLast();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion resume() {
		start();
		return this;
	}

	@Override
	public @Nonnull CompoundMotion setTime(final float time) {
		float t = 0;
		while (true) {
			final IMotion m = nextCurrent();
			if (m!=null) {
				final float d = m.getDuration();
				final float newtime = t+d;
				if (newtime>time) {
					m.setTime(time-t);
					break;
				}
				t = newtime;
			} else {
				stopLast();
				break;
			}
		}
		return this;
	}

	@Override
	public @Nonnull CompoundMotion setAfter(final @Nullable Runnable r) {
		this.after = r;
		return this;
	}

	@Override
	public float getDuration() {
		float d = 0;
		for (final IMotion m : this.tasks)
			d += m.getDuration();
		return d;
	}

	@Override
	public float getEnd(final float start) {
		return getLast();
	}

	@Override
	public @Nullable Runnable getAfter() {
		return this.after;
	}

	@Override
	public void onFinished() {
		if (this.after!=null)
			this.after.run();
	}

	@Override
	public String toString() {
		return String.format("CompoundMotion [paused=%s, tasks=%s, looplast=%s]", this.paused, this.tasks, this.looplast);
	}

	/**
	 * モーションからモーションセットを作成します
	 * @param motions モーション
	 * @return モーションセット
	 */
	public static @Nonnull CompoundMotion of(final @Nonnull IMotion... motions) {
		final CompoundMotion compound = new CompoundMotion();
		for (final IMotion motion : motions)
			if (motion!=null)
				compound.add(motion);
		return compound;
	}

	/**
	 * 初期値とモーションからモーションセットを作成します
	 * @param coord 初期値
	 * @param motions モーション
	 * @return モーションセット
	 */
	public static @Nonnull CompoundMotion of(final float coord, final @Nonnull IMotion... motions) {
		final CompoundMotion compound = new CompoundMotion(coord);
		for (final IMotion motion : motions)
			if (motion!=null)
				compound.add(motion);
		return compound;
	}

	/**
	 * モーションタスク
	 * @param <E> モーション
	 * @author TeamFruit
	 */
	public static class TaskList<E> implements Iterable<E> {

		private final @Nonnull List<E> tasks = Lists.newArrayList();
		private int pos;

		public void add(final @Nonnull E e) {
			this.tasks.add(e);
		}

		protected @Nullable E get(final int pos) {
			if (0<=pos&&pos<this.tasks.size())
				return this.tasks.get(pos);
			return null;
		}

		public @Nullable E get() {
			return get(this.pos);
		}

		public @Nullable E poll() {
			if (isEmpty())
				return get();
			return get(this.pos++);
		}

		public void reset() {
			this.tasks.clear();
			restart();
		}

		public void restart() {
			this.pos = 0;
		}

		public void finish() {
			this.pos = this.tasks.size();
		}

		public @Nullable E getLast() {
			return get(this.tasks.size()-1);
		}

		public boolean isEmpty() {
			return this.tasks.isEmpty();
		}

		public boolean isFinished() {
			return this.pos>this.tasks.size();
		}

		public boolean isLast() {
			return this.pos>=this.tasks.size();
		}

		@Override
		public @Nullable Iterator<E> iterator() {
			return this.tasks.iterator();
		}

		@Override
		public String toString() {
			return String.format("TaskList [tasks=%s, pos=%s]", this.tasks, this.pos);
		}

	}
}