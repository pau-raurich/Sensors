package cat.dam.pau.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class ObjectePilota extends View implements SensorEventListener {
    Paint pincel = new Paint();
    int altura, amplada;
    int tamany = 40;
    int borde = 12;
    float ejeX=0, ejeY=0,ejeZ=0;
    String x,y,z;
    public ObjectePilota(Context interfaz){
        super(interfaz);
        SensorManager snAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotacion = snAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        snAdministrador.registerListener(this,snsRotacion,SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext() .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        amplada =pantalla.getWidth();
        altura =pantalla.getHeight();
    }
    @Override
    public void onSensorChanged(SensorEvent canvi){
        ejeX-=canvi.values[0];
        x=Float.toString(ejeX);
        if (ejeX<(tamany +borde)){
            ejeX=(tamany +borde);
        }else if (ejeX>(amplada -(tamany +borde))){
            ejeX=(amplada -(tamany +borde));
        }
        ejeY+=canvi.values[1];
        y=Float.toString(ejeY);
        if (ejeY<(tamany +borde)){
            ejeY=(tamany +borde);
        }else if(ejeY>(amplada -(tamany +170))){
            ejeY=(amplada -(tamany +170));
        }
        ejeZ=canvi.values[2];
        z=String.format("%.2f",ejeZ);
        invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onDraw(Canvas dibuix){
        pincel.setColor(Color.RED);
        dibuix.drawCircle(ejeX,ejeY, ejeZ+ tamany,pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(25);
        dibuix.drawText("PauRaurich", ejeX-35,ejeY+3,pincel);
    }
}

