package ru.vsu.checkers.model;

import java.util.List;

public record Move(List<MicroStep> path) {

    @Override
    public int hashCode() {
        int answer = 31;
        for(MicroStep st : path){
            answer*= st.hashCode();
        }
        return answer;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        Move m = (Move) obj;
        if(this.path.size() != m.path.size()) return false;
        for(int i = 0; i < path.size(); i++){
            if(path.get(i) != m.path().get(i))return false;
        }
        return true;
    }
}
