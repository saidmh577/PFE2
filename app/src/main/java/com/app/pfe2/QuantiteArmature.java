package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class QuantiteArmature extends AppCompatActivity {

    EditText b, h, nu, mu, fc28, fe, d, dp;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantite_armature);

        b = findViewById(R.id.b);
        h = findViewById(R.id.h);
        nu = findViewById(R.id.n0);
        mu = findViewById(R.id.m0);
        fc28 = findViewById(R.id.fc28);
        fe = findViewById(R.id.fe);
        d = findViewById(R.id.d);
        dp = findViewById(R.id.dp);
        spinner = findViewById(R.id.spinner);
    }

    public void calculer(View view) {
        boolean acc = (spinner.getSelectedItemPosition() == 0) ? true : false;
        String values[] = calcul(
                Double.parseDouble(b.getText().toString()),
                Double.parseDouble(h.getText().toString()),
                Double.parseDouble(nu.getText().toString()),
                Double.parseDouble(mu.getText().toString()),
                Double.parseDouble(fc28.getText().toString()),
                Double.parseDouble(fe.getText().toString()),
                Double.parseDouble(d.getText().toString()),
                Double.parseDouble(dp.getText().toString()),
                acc
        );

        Intent intent = new Intent(this, ResultatQuantiteArmature.class);
        intent.putExtra("as", values[0]);
        intent.putExtra("asp", values[1]);
        intent.putExtra("msg", values[2]);
        startActivity(intent);
    }

    public double Max(double a,double b){
        double M;
        if (a>b){
            M = a;
        }else {
            M=b;
        }
        return M;
    }

    public String[] calcul(double b,double h,double Nu,double Mu,double fc_28,double fe,double d,double d_p,boolean accid){
        double e,psi,sigma_bc=0,zeta,eNc,Mu_fictif,As=0,As_p=0,
                As_fictif,As_1=0,sigma_s=0.1,ft_28,x,sigma_s_p,gama_s=0.1;
        String msg;
        if (accid = true) {

        }

        e = Mu / Nu;
        psi= Nu/(b*h*sigma_bc);

        if (psi<=0.18){
            if (psi<=2/3){
                zeta=(1+Math.sqrt(9-12*psi))/(4*(3+Math.sqrt(9-12*psi)));
            }
            else {
                zeta =(3*psi-1)*(1-psi)/(4*psi);
            }
            eNc=zeta*h;
            if(e>eNc){
                msg = "La section est partielleent comprimée (flexion simple)";

                Mu_fictif = Nu*((e+b+h)/2);
                As_p = (Mu-0.391*b*d*d*sigma_bc)/348*(d-d_p);
                As_fictif = As_p + (b*d*sigma_bc/651);
                As_1 = As_fictif - Nu/sigma_s;
                ft_28 = 0.6 + 0.06*fc_28;
                if(As<0){
                    As = As_1;
                }
                else{
                    As = Max((b*h)/1000,0.23*b*d*ft_28/fe);
                }
            }
            else{
                msg= "La section est entièrement comprimée";
            }
        }
        else{
            zeta = e/h;
            x = 1.32*(0.4-(0.4-zeta))*psi;
            if (x<0.19){
                msg = "La section est entièremant tendue";
                if (x>=0){
                    if (x>=0.004){
                        sigma_s_p = fe/gama_s;
                    }
                    else{
                        sigma_s_p = 400+526*Math.sqrt(x);
                    }
                    As_p = (Nu-(1-x)*b*h*sigma_bc)/sigma_s_p;
                }
                else{
                    sigma_s_p = fe/gama_s;
                    As_p = (Nu*(d-h/2+e)-b*h*sigma_bc*(d-h/2))/((d-d_p)*sigma_s_p);
                    As = (Nu - b*h*sigma_bc)/sigma_s_p;

                }
            }
            else{
                msg = "La section est partielleent comprimée (flexion simple)";

                Mu_fictif = Nu*((e+b+h)/2);
                As_p = (Mu-0.391*b*d*d*sigma_bc)/348*(d-d_p);
                As_fictif = As_p + (b*d*sigma_bc/651);
                As_1 = As_fictif - Nu/sigma_s;
                ft_28 = 0.6 + 0.06*fc_28;
                if(As<0){
                    As = As_1;
                }
                else{
                    As = Max((b*h)/1000,0.23*b*d*ft_28/fe);
                }
            }
        }

        return new String[]{As+"", As_p+"", msg};
    }
}