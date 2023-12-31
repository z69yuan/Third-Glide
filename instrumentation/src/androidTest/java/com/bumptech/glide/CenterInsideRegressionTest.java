package com.bumptech.glide;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.bumptech.glide.test.BitmapRegressionTester;
import com.bumptech.glide.test.CanonicalBitmap;
import com.bumptech.glide.test.GlideApp;
import com.bumptech.glide.test.RegressionTest;
import com.bumptech.glide.test.SplitByCpu;
import com.bumptech.glide.test.SplitBySdk;
import com.bumptech.glide.testutil.TearDownGlide;
import java.util.concurrent.ExecutionException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SplitByCpu
@SplitBySdk({24, 21, 16})
@RegressionTest
public class CenterInsideRegressionTest {
  @Rule public final TestName testName = new TestName();
  @Rule public final TearDownGlide tearDownGlide = new TearDownGlide();
  private BitmapRegressionTester bitmapRegressionTester;
  private Context context;
  private CanonicalBitmap canonical;

  @Before
  public void setUp() {
    context = ApplicationProvider.getApplicationContext();
    bitmapRegressionTester =
        BitmapRegressionTester.newInstance(getClass(), testName).assumeShouldRun();
    canonical = new CanonicalBitmap();
  }

  @Test
  public void centerInside_withSquareSmallerThanImage_returnsImageFitWithinSquare()
      throws ExecutionException, InterruptedException {

    Bitmap result =
        bitmapRegressionTester.test(
            GlideApp.with(context)
                .asBitmap()
                .load(canonical.getBitmap())
                .centerInside()
                .override(50));

    assertThat(result.getWidth()).isEqualTo(50);
    assertThat(result.getHeight()).isEqualTo(37);
  }

  @Test
  public void centerInside_withSquareLargerThanImage_returnsOriginalImage()
      throws ExecutionException, InterruptedException {
    float multiplier = 1.1f;
    int multipliedWidth = (int) (canonical.getWidth() * multiplier);
    Bitmap result =
        bitmapRegressionTester.test(
            GlideApp.with(context)
                .asBitmap()
                .load(canonical.getBitmap())
                .centerInside()
                .override(multipliedWidth));

    assertThat(result.getWidth()).isEqualTo(canonical.getWidth());
    assertThat(result.getHeight()).isEqualTo(canonical.getHeight());
  }

  @Test
  public void centerInside_withNarrowRectangle_fitsWithinMaintainingAspectRatio()
      throws ExecutionException, InterruptedException {
    Bitmap result =
        bitmapRegressionTester.test(
            GlideApp.with(context)
                .asBitmap()
                .load(canonical.getBitmap())
                .centerInside()
                .override(canonical.getWidth() / 10, canonical.getHeight()));

    assertThat(result.getWidth()).isEqualTo(canonical.getWidth() / 10);
    assertThat(result.getHeight()).isEqualTo(canonical.getHeight() / 10);
  }

  @Test
  public void centerInside_withShortRectangle_fitsWithinMaintainingAspectRatio()
      throws ExecutionException, InterruptedException {
    Bitmap result =
        bitmapRegressionTester.test(
            GlideApp.with(context)
                .asBitmap()
                .load(canonical.getBitmap())
                .centerInside()
                .override(canonical.getWidth(), canonical.getHeight() / 2));

    assertThat(result.getWidth()).isEqualTo(canonical.getWidth() / 2);
    assertThat(result.getHeight()).isEqualTo(canonical.getHeight() / 2);
  }
}
