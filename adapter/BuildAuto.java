package adapter;

// For data protection
// When BuildAuto receives input from CreateAuto and UpdateAuto, it will delegate to ProxyAutomobile for declaration
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, EditOptionsMethods {

}
