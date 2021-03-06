
public class Retangulo extends FiguraGeometrica{
	
	private float base;
	private float altura;
	
	public float getBase() {
		return base;
	}
	public void setBase(float base) {
		this.base = base;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public float area() {
		return base * altura;
	}
	public float perimetro() {
		return base * 2 + altura * 2;
	}
}
