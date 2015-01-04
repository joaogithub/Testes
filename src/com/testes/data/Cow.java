package com.testes.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Cow implements Parcelable{

	public String nombre;
	public String padre;
	public String madre;
	public String getMadre() {
		return madre;
	}
	public void setMadre(String madre) {
		this.madre = madre;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getNumero_corto() {
		return numero_corto;
	}
	public void setNumero_corto(String numero_corto) {
		this.numero_corto = numero_corto;
	}
	public int numero_completo;
	public String fecha_nacimiento;
	public String numero_corto;

	public Cow(long long1, String string, String string2, String string3,
			int int1, int int2, int int3) {
		fecha_nacimiento = string;
		numero_corto = string2;
		numero_completo = int1;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public int getNumero_completo() {
		return numero_completo;
	}
	public void setNumero_completo(int numero_completo) {
		this.numero_completo = numero_completo;
	}

	public class Vacas{
		public static final String NOMBRE = "nombre";
		public static final String NUMERO_CORTO = "numerocorto";
		public static final String NUMERO_COMPLETO = "numerocompleto";
		public static final String PADRE = "padre";
		public static final String MADRE = "madre";
		public static final String FECHA_NACIMIENTO = "fechanascimento";
	}


	protected Cow(Parcel in) {
		nombre = in.readString();
		padre = in.readString();
		madre = in.readString();
		numero_completo = in.readInt();
		fecha_nacimiento = in.readString();
		numero_corto = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nombre);
		dest.writeString(padre);
		dest.writeString(madre);
		dest.writeInt(numero_completo);
		dest.writeString(fecha_nacimiento);
		dest.writeString(numero_corto);

	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Cow> CREATOR = new Parcelable.Creator<Cow>() {
		@Override
		public Cow createFromParcel(Parcel in) {
			return new Cow(in);
		}

		@Override
		public Cow[] newArray(int size) {
			return new Cow[size];
		}
	};

}
